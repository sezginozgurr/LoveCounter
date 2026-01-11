package com.example.lovecounter.presentation.memories

import com.example.lovecounter.data.model.MemoryEntity

fun getFakeMemories(): List<MemoryEntity> {
    return listOf(
        MemoryEntity(
            id = 1,
            title = "Our first trip",
            subtitle = "We went to the beach and had a great time.",
            photoUris = emptyList()
        ),
        MemoryEntity(
            id = 2,
            title = "My birthday",
            subtitle = "You threw me a surprise party!",
            photoUris = emptyList()
        ),
        MemoryEntity(
            id = 3,
            title = "Our anniversary",
            subtitle = "We had a romantic dinner.",
            photoUris = emptyList()
        )
    )
}
