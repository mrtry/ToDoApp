package io.github.mrtry.todolist.misc.extension

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map


// see: https://stackoverflow.com/a/57439864
fun <T> CollectionReference.getDataFlow(mapper: (QuerySnapshot?) -> T): Flow<T> {
    return getQuerySnapshotFlow()
        .map {
            return@map mapper(it)
        }
}

// see: https://stackoverflow.com/a/57439864
fun CollectionReference.getQuerySnapshotFlow(): Flow<QuerySnapshot?> {
    return callbackFlow {
        val listenerRegistration =
            addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    cancel(
                        message = firebaseFirestoreException.message.toString(),
                        cause = firebaseFirestoreException
                    )
                    return@addSnapshotListener
                }

                offer(querySnapshot)
            }
        awaitClose {
            listenerRegistration.remove()
        }
    }
}