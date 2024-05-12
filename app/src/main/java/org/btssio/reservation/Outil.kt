package org.btssio.reservation

data class Outil(
    val id: Long,
    val nom: String,
    val description: String,
    var isReserved: Boolean // Ajout de la propriété isReserved
)




