package org.btssio.reservation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import org.btssio.reservation.Outil

class OutilAdapter(context: Context, resource: Int, objects: List<Outil>) :
    ArrayAdapter<Outil>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(android.R.layout.simple_spinner_item, null)
        }

        val outil = getItem(position)
        val textView = view?.findViewById<TextView>(android.R.id.text1)
        textView?.text = outil?.nom

        return view!!
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, null)
        }

        val outil = getItem(position)
        val textView = view?.findViewById<TextView>(android.R.id.text1)
        textView?.text = outil?.nom

        return view!!
    }
}
