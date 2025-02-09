package ru.anishark.app.presentation.filter.expandablelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.CheckBox
import android.widget.ExpandableListView
import android.widget.TextView
import ru.anishark.app.R

class ExpandableListAdapter(
    private val context: Context,
    private var expandableListTitle: List<String>,
    private var expandableListDetail: Map<String, List<String>>,
) : BaseExpandableListAdapter() {
    private val checkBoxStates = mutableMapOf<String, MutableMap<Int, Boolean>>()
    val selectedRatings = mutableSetOf<String>()
    val selectedTypes = mutableSetOf<String>()
    val selectedGenres = mutableSetOf<String>()

    init {
        expandableListTitle.forEach { header ->
            checkBoxStates[header] = mutableMapOf()
        }
    }

    override fun getChild(
        listPosition: Int,
        expandedListPosition: Int,
    ): Any = this.expandableListDetail[this.expandableListTitle[listPosition]]!![expandedListPosition]

    override fun getChildId(
        listPosition: Int,
        expandedListPosition: Int,
    ): Long = expandedListPosition.toLong()

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        var convertView = convertView
        val expandedListText = getChild(listPosition, expandedListPosition) as String
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.filter_child_item, null)
        }

        val textView = convertView!!.findViewById<TextView>(R.id.childTextView)
        val checkBox = convertView.findViewById<CheckBox>(R.id.checkBox)
        textView.text = expandedListText

        val groupName = expandableListTitle[listPosition]
        checkBox.setOnCheckedChangeListener(null)
        checkBox.isChecked = checkBoxStates[groupName]?.get(expandedListPosition) ?: false
        checkBox.isEnabled = (parent as ExpandableListView).isGroupExpanded(listPosition)

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBoxStates[groupName]?.keys?.forEach {
                    checkBoxStates[groupName]?.set(it, false)
                }
                checkBoxStates[groupName]?.set(expandedListPosition, true)

                when (groupName) {
                    "Rating:" -> {
                        selectedRatings.clear()
                        selectedRatings.add(expandedListText)
                    }

                    "Types:" -> {
                        selectedTypes.clear()
                        selectedTypes.add(expandedListText)
                    }

                    "Genres:" -> {
                        selectedGenres.add(expandedListText)
                    }
                }
            } else {
                checkBoxStates[groupName]?.set(expandedListPosition, false)
                when (groupName) {
                    "Rating:" -> {
                        selectedRatings.remove(expandedListText)
                    }

                    "Types:" -> {
                        selectedTypes.remove(expandedListText)
                    }

                    "Genres:" -> {
                        selectedGenres.remove(expandedListText)
                    }
                }
            }
            notifyDataSetChanged()
        }

        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int = this.expandableListDetail[this.expandableListTitle[listPosition]]!!.size

    override fun getGroup(listPosition: Int): Any = this.expandableListTitle[listPosition]

    override fun getGroupCount(): Int = this.expandableListTitle.size

    override fun getGroupId(listPosition: Int): Long = listPosition.toLong()

    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        var convertView = convertView
        val listTitle = getGroup(listPosition) as String
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.filter_group_item, null)
        }
        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.listTitle)
        listTitleTextView.text = listTitle
        return convertView
    }

    override fun hasStableIds(): Boolean = false

    override fun isChildSelectable(
        listPosition: Int,
        expandedListPosition: Int,
    ): Boolean = true

    fun updateData(newData: Map<String, List<String>>) {
        expandableListDetail = newData
        expandableListTitle = ArrayList(newData.keys)
        notifyDataSetChanged()
    }
}
