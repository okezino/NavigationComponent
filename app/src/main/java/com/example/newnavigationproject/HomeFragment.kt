package com.example.newnavigationproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController


class HomeFragment : Fragment() {

    lateinit var textViewButton : TextView
    lateinit var sharedTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewButton = view.findViewById(R.id.homeFragmentTextview)
        sharedTextView = view.findViewById(R.id.homeFragmentSharedValue)
        var repository = Repository("first")
        var HomeFactory = MainViewModelFactory(repository)
        val mainViewModel : MainViewModel by activityViewModels{
            HomeFactory
        }

        sharedTextView.setOnClickListener {
            mainViewModel.updateCount()
        }

        mainViewModel.count.observe(viewLifecycleOwner, {
            sharedTextView.text = it.toString()
        })

        textViewButton.setOnClickListener {

            var person = Person("Explore Page", 38)
            val action = HomeFragmentDirections.actionHomeFragmentToExploreFragment("DevJava",70, person )
            findNavController().navigate(action)
        }
    }


}