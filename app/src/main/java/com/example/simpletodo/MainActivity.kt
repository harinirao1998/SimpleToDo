package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File;
import java.io.IOException
import java.io.writeLines;
import java.io.readLines;
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    var listOfTasks = mutableListOf<String>()
    lateinit var adapter : TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener=object:TaskItemAdapter.OnLongClickListener{
            override fun onItemLongClicked(position: Int) {
                listOfTasks.removeAt(position)
                adapter.notifyDataSetChanged()
                saveItems()
            }
        }
        //1. let's detect when the user clicks the add button
        //findViewById<Button>(R.id.button).setOnClickListener{
            //code in here will be executed when user clicks a button
          //   Log.i( "Caren", "User clicked on button")
        //}
        loadItems()


        //lookup recycler view by layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // Create adapter passing in the sample user data
        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)
        // Attach the adapter to the recyclerview to populate items
        recyclerView.adapter = adapter
        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)
        //grabbing a reference to the button and setting an onclick listener to the button

        findViewById<Button>(R.id.button).setOnClickListener{
            //1. grab the text user has inputted into @id/addTaskfield
            val userInputtedTask = findViewById<EditText>(R.id.addTaskField).text.toString();

            //2. add the string to the list of tasks : listOfTasks
            listOfTasks.add(userInputtedTask);

            //notify the adapter that our data has been adapted
            adapter.notifyItemInserted((listOfTasks.size-1))

            //3. Reset text field
            inputTextField.setText(" ")
            saveItems()
        }

    }
    //save the data the user has inputted
    //save data by writing and reading from a file

    //get the file we need
    fun getDataFile(): File{

        return File(filesDir, "data.txt")
    }

    //load items by reading every line in our file
    fun loadItems(){
        try{
            listOfTasks=FileUtils.readLines(getDataFile(), Charset.defaultCharset()
        }catch(ioException: IOException){
            ioException.printStackTrace()
        }
    }

    //save all our items by writing things to a file
    fun saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }
}