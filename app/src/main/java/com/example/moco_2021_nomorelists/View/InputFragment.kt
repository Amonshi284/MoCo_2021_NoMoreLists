package com.example.moco_2021_nomorelists.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.moco_2021_nomorelists.Model.User
import com.example.moco_2021_nomorelists.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InputFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        val buttonSave = view?.findViewById<Button>(R.id.buttonSave)
        val editTextName = view?.findViewById<EditText>(R.id.editTextName)
        val editTextStreet = view?.findViewById<EditText>(R.id.editTextStreet)
        val editTextCity = view?.findViewById<EditText>(R.id.editTextCity)
        val editTextZIP = view?.findViewById<EditText>(R.id.editTextZIP)
        val editTextPhone = view?.findViewById<EditText>(R.id.editTextPhone)
        val editTextEmail = view?.findViewById<EditText>(R.id.editTextEmailAddress)

        buttonSave?.setOnClickListener {
            var user = User(null,
                editTextName?.text.toString(),
                editTextStreet?.text.toString(),
                editTextCity?.text.toString(),
                editTextZIP?.text.toString().toInt(),
                editTextPhone?.text.toString().toInt(),
                editTextEmail?.text.toString())


        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InputFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InputFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}