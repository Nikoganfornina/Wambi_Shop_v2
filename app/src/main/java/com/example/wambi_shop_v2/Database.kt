package com.example.wambi_shop_v2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database {

    // Open/create the database
    fun openDatabase(context: Context): SQLiteDatabase {
        val dbHelper = DBHelper(context)
        return dbHelper.writableDatabase
    }

    // DBHelper class for creating the database
    class DBHelper(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase) {
            // Create tables
            db.beginTransaction()
            try {
                createProductosTable(db)
                createUsuariosTable(db)
                createPedidosTable(db)
                insertInitialData(db)
                db.setTransactionSuccessful()
            } finally {
                db.endTransaction()
            }
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTOS")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_PEDIDOS")
            onCreate(db)
        }

        // Create the products table
        private fun createProductosTable(db: SQLiteDatabase) {
            val createTableQuery = """
                CREATE TABLE IF NOT EXISTS $TABLE_PRODUCTOS (
                    $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $COLUMN_NOMBRE TEXT NOT NULL,
                    $COLUMN_CATEGORIA TEXT NOT NULL,
                    $COLUMN_PRECIO REAL NOT NULL,
                    $COLUMN_STOCK INTEGER NOT NULL,
                    $COLUMN_DESCRIPCION TEXT,
                    $COLUMN_IMAGEN TEXT
                );
            """.trimIndent()
            db.execSQL(createTableQuery)
        }

        // Create the users table
        private fun createUsuariosTable(db: SQLiteDatabase) {
            val createTableQuery = """
                CREATE TABLE IF NOT EXISTS $TABLE_USUARIOS (
                    $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $COLUMN_NOMBRE TEXT NOT NULL,
                    $COLUMN_EMAIL TEXT NOT NULL,
                    $COLUMN_CONTRASENA TEXT NOT NULL,
                    $COLUMN_TELEFONO TEXT
                );
            """.trimIndent()
            db.execSQL(createTableQuery)
        }

         fun verificarUsuario(email: String, contrasena: String):Boolean{

             val db=this.readableDatabase
             val query="SELECT email, contrasena FROM usuarios WHERE email = ?"
             val cursor = db.rawQuery(query, arrayOf(email))
             var ret=false
             if(cursor.moveToFirst()) if(cursor.getString(1) == contrasena) ret=true
             db.close()
             cursor.close()
             return ret
        }
        // Create the orders table
        private fun createPedidosTable(db: SQLiteDatabase) {
            val createTableQuery = """
                CREATE TABLE IF NOT EXISTS $TABLE_PEDIDOS (
                    $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $COLUMN_CLIENTE_ID INTEGER NOT NULL,
                    $COLUMN_FECHA TEXT NOT NULL,
                    $COLUMN_TOTAL REAL NOT NULL,
                    FOREIGN KEY($COLUMN_CLIENTE_ID) REFERENCES $TABLE_USUARIOS($COLUMN_ID)
                );
            """.trimIndent()
            db.execSQL(createTableQuery)
        }

        // Insert initial data
        private fun insertInitialData(db: SQLiteDatabase) {
            insertInitialProductos(db)
            insertInitialUsuarios(db)
        }

        // Insert initial products
        private fun insertInitialProductos(db: SQLiteDatabase) {
            val insertStatement = """
        INSERT INTO $TABLE_PRODUCTOS ($COLUMN_NOMBRE, $COLUMN_CATEGORIA, $COLUMN_PRECIO, $COLUMN_STOCK, $COLUMN_DESCRIPCION, $COLUMN_IMAGEN) VALUES
        
        -- Frozen
        ('gyozas de pollo', 'congelados', 6.80, 50, 'Delicious chicken-filled gyozas, perfect for quick and healthy snacks.', 'congelado1.png'),
        ('gyozas de ternera', 'congelados', 6.99, 25, 'Juicy beef-filled gyozas, perfect to pair with soy sauce.', 'congelado2.png'),
        ('wanton de pato', 'congelados', 5.99, 47, 'Crispy duck-filled wantons, an explosion of flavor in every bite.', 'congelado3.png'),
        ('langostinos rebozados', 'congelados', 9.99, 30, 'Crispy and tasty shrimp, perfect for any occasion.', 'congelado4.png'),
        ('pan bao de ternera', 'congelados', 7.50, 52, 'Bao buns filled with juicy beef, perfect for a quick meal.', 'congelado5.png'),
        ('pan bao de pollo', 'congelados', 7.20, 34, 'Bao buns filled with tender chicken, delicious and easy to prepare.', 'congelado6.png'),
        
        -- Sweets
        
        ('dorayaki de cacao', 'dulces', 4.30, 25, 'Sweet dorayakis filled with cocoa, perfect for pairing with tea.', 'dulce1.png'),
        ('dorayaki de haba', 'dulces', 5.60, 16, 'Dorayakis filled with red bean paste, a different flavor for sweet lovers.', 'dulce2.png'),
        ('mochi de matcha', 'dulces', 6.55, 34, 'Matcha-filled mochi, a traditional Japanese touch.', 'dulce3.png'),
        ('mochi de tarta de queso', 'dulces', 7.20, 36, 'Mochi filled with cheesecake, an explosion of creamy flavor.', 'dulce4.png'),
        ('pastel de luna', 'dulces', 8.00, 12, 'Traditional mooncake, ideal for special celebrations.', 'dulce5.png'),
        ('bingsu', 'dulces', 4.90, 36, 'Ice dessert with a soft texture, perfect for hot days.', 'dulce6.png'),
        
        -- Noodles
        
        ('fideos de ramen planos', 'fideos', 5.00, 20, 'Flat ramen noodles, perfect for soups with a different touch.', 'fideos1.png'),
        ('fideos de ramen', 'fideos', 4.65, 35, 'Classic ramen noodles, perfect for any Japanese recipe.', 'fideos2.png'),
        ('fideos de udon', 'fideos', 4.99, 43, 'Thick udon noodles, perfect for stews or soups.', 'fideos3.png'),
        ('fideos de arroz', 'fideos', 4.30, 19, 'Light rice noodles, perfect for stir-fries.', 'fideos4.png'),
        ('fideos de yakisoba', 'fideos', 5.10, 27, 'Yakisoba noodles ideal for stir-fries with vegetables and meat.', 'fideos5.png'),
        ('fideos integrales', 'fideos', 6.30, 36, 'Whole wheat noodles for healthier options.', 'fideos6.png'),
        
        -- Fruits and Vegetables
        
        ('setas shitakes', 'fruta y verduras', 6.30, 36, 'Fresh shitake mushrooms, ideal for stews and soups.', 'verdura1.png'),
        ('tofu blanco', 'fruta y verduras', 5.80, 15, 'Soft white tofu, ideal for vegan recipes.', 'verdura2.png'),
        ('jengibre', 'fruta y verduras', 5.10, 13, 'Fresh ginger, essential in Asian cooking.', 'verdura3.png'),
        ('col china', 'fruta y verduras', 3.10, 21, 'Fresh Chinese cabbage, perfect for stir-fries and soups.', 'verdura4.png'),
        ('lychee', 'fruta y verduras', 9.99, 36, 'Sweet exotic fruit, ideal for desserts or as a snack.', 'verdura5.png'),
        ('alga wakame', 'fruta y verduras', 5.90, 14, 'Dehydrated wakame seaweed, ideal for soups and salads.', 'verdura6.png'),
        
        -- Dishes
        
        ('pollo con curry', 'platos', 6.90, 8, 'Spicy curry chicken, perfect to serve with rice.', 'plato1.png'),
        ('variado de sushi', 'platos', 15.50, 10, 'Variety of fresh sushi, perfect for sharing.', 'plato2.png'),
        ('ramen de miso', 'platos', 6.50, 9, 'Miso broth ramen, a Japanese classic.', 'plato3.png'),
        ('pad thai', 'platos', 7.00, 14, 'Pad Thai with traditional sauce, an explosion of flavors.', 'plato4.png'),
        ('rollitos de primavera', 'platos', 3.50, 12, 'Crispy spring rolls, perfect for snacking.', 'plato5.png'),
        ('pato pekin', 'platos', 10.50, 5, 'Peking duck, tender and juicy.', 'plato6.png'),
        
        -- Utensils
        
        ('palillos de madera', 'utensilios', 3.00, 20, 'Reusable wooden chopsticks, light and practical.', 'utensilio1.png'),
        ('palillos de metal', 'utensilios', 3.99, 13, 'Durable metal chopsticks, ideal for Asian meals.', 'utensilio2.png'),
        ('bol pequeño', 'utensilios', 4.20, 36, 'Small bowl for desserts or snacks.', 'utensilio3.png'),
        ('bol de ramen', 'utensilios', 7.00, 41, 'Large bowl perfect for serving ramen.', 'utensilio4.png'),
        ('plato rectangular', 'utensilios', 5.60, 25, 'Rectangular plate ideal for sushi or appetizers.', 'utensilio5.png'),
        ('makisu', 'utensilios', 2.80, 15, 'Makisu for easily preparing sushi.', 'utensilio6.png');
    """.trimIndent()
            db.execSQL(insertStatement)
        }

        // Insert initial users
        private fun insertInitialUsuarios(db: SQLiteDatabase) {
            val insertStatement = """
        INSERT INTO $TABLE_USUARIOS ($COLUMN_NOMBRE, $COLUMN_EMAIL, $COLUMN_TELEFONO ,  $COLUMN_CONTRASENA ) VALUES
        ('Juan Pérez', 'juan.perez@mail.com', '123456789', '1234'),
        ('Ana Gómez', 'ana.gomez@mail.com', '987654321', '1234');
        """.trimIndent()
            db.execSQL(insertStatement)
        }
    }

    // Database constants
    companion object {
        const val DATABASE_NAME = "productosDB"
        const val DATABASE_VERSION = 1

        // Products Table
        const val TABLE_PRODUCTOS = "productos"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_CATEGORIA = "categoria"
        const val COLUMN_PRECIO = "precio"
        const val COLUMN_STOCK = "stock"
        const val COLUMN_DESCRIPCION = "descripcion"
        const val COLUMN_IMAGEN = "imagen"

        // Users Table
        const val TABLE_USUARIOS = "usuarios"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_TELEFONO = "telefono"
        const val COLUMN_CONTRASENA = "contrasena"


        // Orders Table
        const val TABLE_PEDIDOS = "pedidos"
        const val COLUMN_CLIENTE_ID = "cliente_id"
        const val COLUMN_FECHA = "fecha"
        const val COLUMN_TOTAL = "total"
    }
}
