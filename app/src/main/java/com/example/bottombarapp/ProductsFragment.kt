package com.example.bottombarapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.Navigation

/**
 * A simple [Fragment] subclass.
 */
class ProductsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var dtailsBtn = view.findViewById<Button>(R.id.gotodetailsId)
        val navControl = Navigation.findNavController(view)
        val bundle = bundleOf("productdetails" to "hi i am rayhan")


        dtailsBtn.setOnClickListener {

            navControl.navigate(R.id.action_productsFragment_to_detailsFragment, bundle)
        }
    }

}
