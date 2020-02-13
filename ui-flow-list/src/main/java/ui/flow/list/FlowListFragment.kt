package ui.flow.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ui.navigation.DEEPLINK_NEW_FLOW_TITLE
import ui.navigation.navigate

class FlowListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_flow_list, container, false)

        view.findViewById<FloatingActionButton>(R.id.fab_add_new_flow)
            .setOnClickListener { navigate(this, DEEPLINK_NEW_FLOW_TITLE) }
        return view
    }
}