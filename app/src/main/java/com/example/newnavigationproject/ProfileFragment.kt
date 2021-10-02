package com.example.newnavigationproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar


class ProfileFragment : Fragment() {



    lateinit var textViewButton : TextView
    lateinit var mainViewModel : MainViewModel
    lateinit var sharedTextView: TextView
    private val  args : ProfileFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewButton = view.findViewById(R.id.profileFragmentTextview)
        sharedTextView = view.findViewById(R.id.profileFragmentSharedValue)
        var firstDeep = args.rounds

        firstDeep?.let {
            Snackbar.make(view, "Value from link : $it", Snackbar.LENGTH_LONG).show()
        }

        var repository = Repository("first")
        var ProfileFactory = MainViewModelFactory(repository)
        mainViewModel = ViewModelProvider(requireActivity(), ProfileFactory).get(MainViewModel::class.java)

        sharedTextView.setOnClickListener {
            mainViewModel.updateCount()
        }

        mainViewModel.count.observe(viewLifecycleOwner, {
            sharedTextView.text = it.toString()
        })


        textViewButton.setOnClickListener {

            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)

        }


    }


}