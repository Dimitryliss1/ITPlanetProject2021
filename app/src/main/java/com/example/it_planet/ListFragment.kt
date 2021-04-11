package com.example.it_planet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class ListFragment : Fragment() {
    lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.list_fragment, container, false)
        listView = view.findViewById(R.id.userlist)
    /*   val parserVKIT = ParserVKIT()
        var strings: MutableList<String> = parserVKIT.getHtmlFromWeb("https://vkist.guap.ru/")


      val arrayAdapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, strings)
        listView.adapter = arrayAdapter

           listView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
               val link: String = parent.getItemAtPosition(position).toString().substringAfter(' ')
                   .substringBefore(' ')
               listView.adapter = arrayAdapter
               //  val intent = Intent(getActivity(), ArticleActivity::class.java)
               //  startActivity(intent.putExtra("link", link))
           })
*/
        return view
    }

}