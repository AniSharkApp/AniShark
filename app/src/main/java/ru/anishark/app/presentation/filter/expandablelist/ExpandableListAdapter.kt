package ru.anishark.app.presentation.filter.expandablelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.CheckBox
import android.widget.ExpandableListView
import android.widget.TextView
import android.widget.Toast
import ru.anishark.app.R
import ru.anishark.app.presentation.filter.activity.FilterActivity

class ExpandableListAdapter(
    private val context: Context,
    private var expandableListTitle: List<String>,
    private var expandableListDetail: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {

    private val checkBoxStates = mutableMapOf<String, MutableMap<Int, Boolean>>()

    init {
        expandableListTitle.forEach { header ->
            checkBoxStates[header] = mutableMapOf()
        }
    }

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return this.expandableListDetail[this.expandableListTitle[listPosition]]!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
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
            checkBoxStates[groupName]?.set(expandedListPosition, isChecked)
            if (isChecked) {
                Toast.makeText(context, "Checked: $expandedListText", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Unchecked: $expandedListText", Toast.LENGTH_SHORT).show()
            }
        }

        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return this.expandableListDetail[this.expandableListTitle[listPosition]]!!.size
    }

    override fun getGroup(listPosition: Int): Any {
        return this.expandableListTitle[listPosition]
    }

    override fun getGroupCount(): Int {
        return this.expandableListTitle.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
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

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }

    fun enableCheckboxes(groupPosition: Int) {
        val groupName = expandableListTitle[groupPosition]
        expandableListDetail[groupName]?.forEachIndexed { index, _ ->
            checkBoxStates[groupName]?.set(index, checkBoxStates[groupName]?.get(index) ?: false)
        }
        notifyDataSetChanged()
    }

    fun disableCheckboxes(groupPosition: Int) {
        val groupName = expandableListTitle[groupPosition]
        expandableListDetail[groupName]?.forEachIndexed { index, _ ->
            val checkBox =
                (context as FilterActivity).expandableListView.findViewWithTag<CheckBox>("$groupName:$index")
            checkBox?.isEnabled = false
        }
    }

    fun updateData(newData: HashMap<String, List<String>>) {
        expandableListDetail = newData
        expandableListTitle = ArrayList(newData.keys)
        notifyDataSetChanged()
    }
}