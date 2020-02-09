package ui.flow.list

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FlowListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_flow_list, container, false)

        view.findViewById<FloatingActionButton>(R.id.fab_add_new_flow)
            .setOnClickListener {
                val uri = Uri.parse("https://curlybraces.dev/newflow/title")
                findNavController(this).navigate(uri)
            }
        return view
    }
}