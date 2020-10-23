package com.example.firebase.feature_users.domain

import android.util.Log
import com.example.firebase.feature_users.data.User
import com.example.firebase.feature_users.data.UserGroup
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class UsersFBService private constructor(someString: String) {

    val databaseReference: DatabaseReference = Firebase.database.reference
    val usersRef = databaseReference.child("Users")
    val usersGroupRef = databaseReference.child("UsersGroup")
    private var mString: String = someString

    private val TAG = "UsersFBService"

    companion object: SingletonHolder<UsersFBService, String>(::UsersFBService)

    fun createUser(email: String, password: String): String {
        val str = this.javaClass.hashCode()
        return "$str $mString"
    }

    fun addUserToWorkDB(user: User) {
        usersRef.push().setValue(user)
    }

    fun addGroup(group: UserGroup) {
        usersGroupRef.push().setValue(group)
    }

    fun addUserToGroup(user: User, groupName: String): Task<String>{
        val dbSource = TaskCompletionSource<String>()
        val dbTask= dbSource.task

        getAllGroups().addOnSuccessListener {
            it.forEach { ug -> if (ug.name.equals(groupName)){
                // ToDo: not good if we will have to group with the same name
                if (ug.userList != null && !ug.userList!!.contains(user)){
                    ug.userList!!.add(user)
                    upDateUserGroup(ug)
                } else if (ug.userList == null) {
                    ug.userList = arrayListOf()
                    ug.userList!!.add(user)
                    upDateUserGroup(ug)
                }

            } }
            dbSource.setResult("Completed!")
        }

        return dbTask
    }

    private fun upDateUserGroup(userGroup: UserGroup) {
        userGroup.refID?.let { usersGroupRef.child(it).setValue(userGroup) }
    }

    fun getUserList(): Task<List<User>> {
        val dbSource = TaskCompletionSource<List<User>>()
        val dbTask= dbSource.task

        usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read Users value.", error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val userList= arrayListOf<User>()
                for (data in snapshot.children) {
                    data.getValue(User::class.java).let {
                        userList.add(it!!)
                    }
                }
                dbSource.setResult(userList)
            }

        })
        return dbTask
    }

    fun getAllGroups(): Task<List<UserGroup>>{
        val dbSource = TaskCompletionSource<List<UserGroup>>()
        val dbTask= dbSource.task

        usersGroupRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read UserGroup value.", error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val groupList = arrayListOf<UserGroup>()
                for (data in snapshot.children) {
                    data.getValue(UserGroup::class.java).let {
                        it!!.refID = data.key
                        groupList.add(it)
                    }
                }
                dbSource.setResult(groupList)
            }

        })
        return dbTask
    }

    fun getGroupByName(groupName: String): Task<List<UserGroup>>{
        val dbSource = TaskCompletionSource<List<UserGroup>>()
        val dbTask= dbSource.task

        usersGroupRef.orderByChild("name").equalTo(groupName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, "Failed to find GroupName.", error.toException())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val groupList = arrayListOf<UserGroup>()
                    for (data in snapshot.children) {
                        data.getValue(UserGroup::class.java).let {
                            it!!.refID = data.key
                            groupList.add(it)
                        }
                    }
                    dbSource.setResult(groupList)
                }
            })

        return dbTask
    }

    fun putCurrentUser(user: User){

        // not in users -> add
        // if group exist -> add group else make group than add
        getUserList().addOnSuccessListener{
            if (it.contains(user)){
                getGroupByName("admins").addOnSuccessListener {
                    if (it.size > 0){
                        addUserToGroup(user, "admins")
                    } else {
                        val group = UserGroup(name = "admins")
                        addGroup(group)
                        addUserToGroup(user, "admins")
                    }
                }

            } else {
                addUserToWorkDB(user)
                getGroupByName("admins").addOnSuccessListener {
                    if (it.size > 0){
                        addUserToGroup(user, "admins")
                    } else {
                        val group = UserGroup(name = "admins")
                        addGroup(group)
                        addUserToGroup(user, "admins")
                    }
                }
            }
        }



    }
}