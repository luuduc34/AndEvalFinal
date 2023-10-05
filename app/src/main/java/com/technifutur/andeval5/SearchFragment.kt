package com.technifutur.andeval5

import MovieAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technifutur.andeval5.databinding.FragmentSearchBinding
import com.technifutur.andeval5.model.Movie
import com.technifutur.andeval5.service.MovieServiceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    // api key
    private val apiKey = "55530312075972a425f5fa13e21b218f"
    private val movieService by lazy { MovieServiceImpl() }

    // liste pour contenir les données de l'api
    private var movieList: MutableList<Movie> = mutableListOf()

    // image url
    private var imageUrl: String = ""
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)

        // Initialisation de la RecyclerView et de l'adaptateur
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        movieAdapter = MovieAdapter(movieList)
        recyclerView.adapter = movieAdapter

        // lier le bouton à la méthode search
        binding.searchBtn.setOnClickListener {
            val movieName = binding.searchEdittext.text.toString()
            getMoviesAsync(movieName)
        }

        return binding.root
    }

    private fun getMoviesAsync(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = movieService.getMovies(name, apiKey)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val movies = response.body()?.results
                        movies?.let {
                            movieList.clear()
                            movieList.addAll(it)
                            movieAdapter.notifyDataSetChanged() // Mettez à jour l'adaptateur
                        }
                    } else {
                        // Gérez les erreurs ici, par exemple, affichez un message d'erreur à l'utilisateur
                        Log.e("Error", "La requête a échoué.")
                    }
                }
            } catch (e: Exception) {
                // Gérez les exceptions ici, par exemple, affichez un message d'erreur à l'utilisateur
                Log.e("Error", "Une erreur s'est produite : ${e.message}")
            }
        }
    }

}
