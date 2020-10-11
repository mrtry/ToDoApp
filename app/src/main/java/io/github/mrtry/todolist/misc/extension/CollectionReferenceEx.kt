package io.github.mrtry.todolist.misc.extension

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map


// see: https://stackoverflow.com/a/57439864
fun <T> CollectionReference.getDataFlow(
    metadataChanges: MetadataChanges = MetadataChanges.INCLUDE,
    mapper: (QuerySnapshot?) -> T
): Flow<T> {
    return getQuerySnapshotFlow(metadataChanges)
        .map {
            return@map mapper(it)
        }
}

// see: https://stackoverflow.com/a/57439864
fun CollectionReference.getQuerySnapshotFlow(metadataChanges: MetadataChanges): Flow<QuerySnapshot?> {
    return callbackFlow {
        val listenerRegistration =
            addSnapshotListener(metadataChanges) { querySnapshot, exception ->
                if (exception != null) {
                    cancel(
                        message = exception.message.toString(),
                        cause = exception
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