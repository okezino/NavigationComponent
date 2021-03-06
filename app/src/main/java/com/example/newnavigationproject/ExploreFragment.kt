package com.example.newnavigationproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class ExploreFragment : Fragment() {

    lateinit var textViewButton : TextView
    lateinit var sharedText : TextView

    val args : ExploreFragmentArgs by navArgs()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_explore, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewButton = view.findViewById(R.id.exploreFragmentText)
        sharedText = view.findViewById(R.id.exploreFragmentSharedValue)
        var repository = Repository("first")
        var ExploreFactory = MainViewModelFactory(repository)
        val mainViewModel : MainViewModel by activityViewModels{
            ExploreFactory
        }


        sharedText.setOnClickListener {
            mainViewModel.updateCount()
        }

        mainViewModel.count.observe(viewLifecycleOwner, {
            sharedText.text = it.toString()
        })

        textViewButton.text = args.person.name

        textViewButton.setOnClickListener {
            findNavController().navigate(R.id.action_exploreFragment_to_profileFragment)
        }

    }


}