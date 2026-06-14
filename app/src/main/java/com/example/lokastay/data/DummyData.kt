package com.example.lokastay.data

import com.example.lokastay.data.entity.Favorite
import com.example.lokastay.data.entity.Review
import com.example.lokastay.data.entity.User
import com.example.lokastay.data.entity.Villa

object DummyData {

    val users = listOf(
        User(name = "Prinka Shafa", email = "prinka01@gmail.com", phone = "081234560001", password = "123456"),
        User(name = "Zerly Mahesa", email = "zerly02@gmail.com", phone = "081234560002", password = "123456"),
        User(name = "Nabila Putri", email = "nabila03@gmail.com", phone = "081234560003", password = "123456"),
        User(name = "Raka Ananta", email = "raka04@gmail.com", phone = "081234560004", password = "123456"),
        User(name = "Dinda Permata", email = "dinda05@gmail.com", phone = "081234560005", password = "123456"),
        User(name = "Rizky Saputra", email = "rizky06@gmail.com", phone = "081234560006", password = "123456"),
        User(name = "Alya Putri", email = "alya07@gmail.com", phone = "081234560007", password = "123456"),
        User(name = "Fajar Ramadhan", email = "fajar08@gmail.com", phone = "081234560008", password = "123456"),
        User(name = "Mira Lestari", email = "mira09@gmail.com", phone = "081234560009", password = "123456"),
        User(name = "Bagas Wijaya", email = "bagas10@gmail.com", phone = "081234560010", password = "123456")
    )

    val villas = listOf(
        Villa(
            name = "Villa Puncak Asri",
            location = "Bogor - Puncak",
            pricePerNight = 850000.0,
            rating = 4.7f,
            imageUrl = "villa_1",
            description = "Villa nyaman dengan pemandangan pegunungan dan taman luas."
        ),
        Villa(
            name = "Villa Dago Green",
            location = "Bandung - Dago",
            pricePerNight = 1200000.0,
            rating = 4.8f,
            imageUrl = "villa_2",
            description = "Villa modern dengan balkon luas dan udara sejuk."
        ),
        Villa(
            name = "Villa Batu Garden",
            location = "Malang - Batu",
            pricePerNight = 980000.0,
            rating = 4.6f,
            imageUrl = "villa_3",
            description = "Cocok untuk liburan keluarga dengan taman dan area BBQ."
        ),
        Villa(
            name = "Villa Ubud Harmony",
            location = "Bali - Ubud",
            pricePerNight = 1850000.0,
            rating = 4.9f,
            imageUrl = "villa_4",
            description = "Nuansa tropis tenang dengan private pool kecil."
        ),
        Villa(
            name = "Villa Kuta Sunset",
            location = "Bali - Kuta",
            pricePerNight = 1550000.0,
            rating = 4.5f,
            imageUrl = "villa_5",
            description = "Lokasi strategis dekat pusat kuliner dan pantai."
        ),
        Villa(
            name = "Villa Lembang Breeze",
            location = "Bandung - Lembang",
            pricePerNight = 1100000.0,
            rating = 4.7f,
            imageUrl = "villa_6",
            description = "Villa luas dengan view kebun dan area api unggun."
        ),
        Villa(
            name = "Villa Bromo View",
            location = "Pasuruan - Tosari",
            pricePerNight = 1300000.0,
            rating = 4.8f,
            imageUrl = "villa_7",
            description = "Pemandangan gunung dan sunrise yang sangat indah."
        ),
        Villa(
            name = "Villa Ciater Warm",
            location = "Subang - Ciater",
            pricePerNight = 900000.0,
            rating = 4.4f,
            imageUrl = "villa_8",
            description = "Dekat pemandian air panas dan cocok untuk healing."
        ),
        Villa(
            name = "Villa Trawas Hills",
            location = "Mojokerto - Trawas",
            pricePerNight = 950000.0,
            rating = 4.6f,
            imageUrl = "villa_9",
            description = "Udara segar, halaman luas, cocok untuk rombongan kecil."
        ),
        Villa(
            name = "Villa Anyer Bay",
            location = "Banten - Anyer",
            pricePerNight = 1750000.0,
            rating = 4.7f,
            imageUrl = "villa_10",
            description = "View laut dan cocok untuk staycation akhir pekan."
        ),
        Villa(
            name = "Villa Sentul Forest",
            location = "Bogor - Sentul",
            pricePerNight = 1250000.0,
            rating = 4.5f,
            imageUrl = "villa_11",
            description = "Dekat area wisata dan suasana tenang."
        ),
        Villa(
            name = "Villa Seminyak Calm",
            location = "Bali - Seminyak",
            pricePerNight = 2100000.0,
            rating = 4.9f,
            imageUrl = "villa_12",
            description = "Villa premium dengan interior modern minimalis."
        ),
        Villa(
            name = "Villa Dieng Sky",
            location = "Wonosobo - Dieng",
            pricePerNight = 1000000.0,
            rating = 4.6f,
            imageUrl = "villa_13",
            description = "Cuaca dingin, cocok untuk liburan santai."
        ),
        Villa(
            name = "Villa Yogyakarta Heritage",
            location = "Yogyakarta - Kaliurang",
            pricePerNight = 1150000.0,
            rating = 4.5f,
            imageUrl = "villa_14",
            description = "Nuansa tradisional-modern dengan taman yang asri."
        ),
        Villa(
            name = "Villa Guci Natural",
            location = "Tegal - Guci",
            pricePerNight = 890000.0,
            rating = 4.3f,
            imageUrl = "villa_15",
            description = "Dekat wisata air panas dan alam pegunungan."
        )
    )

    val reviews = listOf(
        Review(villaId = 1, username = "Nabila Putri", comment = "Tempatnya bersih dan view bagus.", rating = 4.8f),
        Review(villaId = 1, username = "Raka Ananta", comment = "Cocok untuk keluarga dan suasananya tenang.", rating = 4.6f),
        Review(villaId = 2, username = "Alya Putri", comment = "Interiornya cantik dan nyaman.", rating = 4.9f),
        Review(villaId = 2, username = "Bagas Wijaya", comment = "Udara sejuk, akses juga lumayan mudah.", rating = 4.7f),
        Review(villaId = 3, username = "Mira Lestari", comment = "Halamannya luas, cocok untuk kumpul.", rating = 4.5f),
        Review(villaId = 3, username = "Fajar Ramadhan", comment = "Recommended untuk staycation keluarga.", rating = 4.6f),
        Review(villaId = 4, username = "Prinka Shafa", comment = "Sangat estetik dan menenangkan.", rating = 5.0f),
        Review(villaId = 4, username = "Dinda Permata", comment = "Private dan suasananya enak banget.", rating = 4.9f),
        Review(villaId = 5, username = "Rizky Saputra", comment = "Lokasi strategis tapi tetap nyaman.", rating = 4.3f),
        Review(villaId = 5, username = "Zerly Mahesa", comment = "Bagus untuk short escape.", rating = 4.5f),
        Review(villaId = 6, username = "Nabila Putri", comment = "View kebun dan udaranya mantap.", rating = 4.7f),
        Review(villaId = 6, username = "Alya Putri", comment = "Villa luas, cocok buat rombongan.", rating = 4.8f),
        Review(villaId = 7, username = "Bagas Wijaya", comment = "Sunrise-nya keren banget.", rating = 4.9f),
        Review(villaId = 7, username = "Raka Ananta", comment = "Worth it untuk suasana pegunungan.", rating = 4.8f),
        Review(villaId = 8, username = "Mira Lestari", comment = "Nyaman dan dekat wisata air panas.", rating = 4.2f),
        Review(villaId = 9, username = "Dinda Permata", comment = "Tempatnya adem dan cocok healing.", rating = 4.6f),
        Review(villaId = 10, username = "Fajar Ramadhan", comment = "View lautnya bagus banget.", rating = 4.8f),
        Review(villaId = 11, username = "Rizky Saputra", comment = "Sentul Forest ini cocok buat short trip.", rating = 4.4f),
        Review(villaId = 12, username = "Prinka Shafa", comment = "Premium dan sangat nyaman.", rating = 5.0f),
        Review(villaId = 13, username = "Zerly Mahesa", comment = "Dingin dan cocok buat santai.", rating = 4.6f),
        Review(villaId = 14, username = "Alya Putri", comment = "Nuansanya unik dan homey.", rating = 4.5f),
        Review(villaId = 15, username = "Bagas Wijaya", comment = "Murah dan suasananya oke.", rating = 4.2f)
    )

    val favorites = listOf(
        Favorite(userId = 1, villaId = 4),
        Favorite(userId = 1, villaId = 10),
        Favorite(userId = 2, villaId = 2),
        Favorite(userId = 2, villaId = 12),
        Favorite(userId = 3, villaId = 1),
        Favorite(userId = 4, villaId = 7),
        Favorite(userId = 5, villaId = 6),
        Favorite(userId = 6, villaId = 3),
        Favorite(userId = 7, villaId = 9),
        Favorite(userId = 8, villaId = 11)
    )
}