package com.example.wambi_shop_v2

import Producto
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openDatabase
import android.database.sqlite.SQLiteOpenHelper

// Base de datos creada por Lucas 28/12/24

class Database {

    // Abrir/crear la base de datos
    fun openDatabase(context: Context): SQLiteDatabase {
        val dbHelper = DBHelper(context)
        return dbHelper.writableDatabase
    }

    // Clase DBHelper para crear la base de datos.
    class DBHelper(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase) {
            // Crea las tablas
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

        // Crear la tabla de productos
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

        // Crear la tabla de usuarios
        private fun createUsuariosTable(db: SQLiteDatabase) {
            val createTableQuery = """
                CREATE TABLE IF NOT EXISTS $TABLE_USUARIOS (
                    $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $COLUMN_NOMBRE TEXT ,
                    $COLUMN_EMAIL TEXT NOT NULL,
                    $COLUMN_CONTRASENA TEXT NOT NULL,
                    $COLUMN_TELEFONO TEXT
                );
            """.trimIndent()
            db.execSQL(createTableQuery)
        }

        fun verificarUsuario(email: String, contrasena: String): Boolean {

            val db = this.readableDatabase
            val query = "SELECT email, contrasena FROM usuarios WHERE email = ?"
            val cursor = db.rawQuery(query, arrayOf(email))
            var ret = false
            if (cursor.moveToFirst()) if (cursor.getString(1) == contrasena) ret = true
            db.close()
            cursor.close()
            return ret
        }

        // Crear la tabla de pedidos
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

        // Insertar productos iniciales
        private fun insertInitialData(db: SQLiteDatabase) {
            insertInitialProductos(db)
            insertInitialUsuarios(db)
        }

        // Insert initial products
        private fun insertInitialProductos(db: SQLiteDatabase) {
            val insertStatement = """
        INSERT INTO $TABLE_PRODUCTOS ($COLUMN_NOMBRE, $COLUMN_CATEGORIA, $COLUMN_PRECIO, $COLUMN_STOCK, $COLUMN_DESCRIPCION, $COLUMN_IMAGEN) VALUES
        
        -- Congelados
        ('gyozas de pollo', 'congelados', 6.80, 50, 'Deliciosas gyozas rellenas de pollo, perfectas para meriendas rápidas y saludables.', 'congelado1'),
        ('gyozas de ternera', 'congelados', 6.99, 25, 'Jugosas gyozas rellenas de ternera, perfectas para acompañar con salsa de soja.', 'congelado2'),
        ('wanton de pato', 'congelados', 5.99, 47, 'Crujientes caprichos rellenos de pato, una explosión de sabor en cada bocado.', 'congelado3'),
        ('langostinos rebozados', 'congelados', 9.99, 30, 'Camarones crujientes y sabrosos, perfectos para cualquier ocasión.', 'congelado4'),
        ('pan bao de ternera', 'congelados', 7.50, 52, 'Panes bao rellenos de jugosa carne de res, perfectos para una comida rápida.', 'congelado5'),
        ('pan bao de pollo', 'congelados', 7.20, 34, 'Bollos bao rellenos de pollo tierno, riquísimos y fáciles de preparar.', 'congelado6'),
        
        -- Dulces
        
        ('dorayaki de cacao', 'dulces', 4.30, 25, 'Dulces dorayakis rellenos de cacao, perfectos para maridar con té.', 'dulce1'),
        ('dorayaki de haba', 'dulces', 5.60, 16, 'Dorayakis rellenos de pasta de judías rojas, un sabor diferente para los amantes del dulce.', 'dulce2'),
        ('mochi de matcha', 'dulces', 6.55, 34, 'Mochi relleno de matcha, un toque tradicional japonés.', 'dulce3'),
        ('mochi de tarta de queso', 'dulces', 7.20, 36, 'Mochi relleno de cheesecake, una explosión de sabor cremoso.', 'dulce4'),
        ('pastel de luna', 'dulces', 8.00, 12, 'Tradicional pastel de luna, ideal para celebraciones especiales.', 'dulce5'),
        ('bingsu', 'dulces', 4.90, 36, 'Postre helado de textura suave, perfecto para los días de calor.', 'dulce6'),
        
        -- Fideos
        
        ('fideos de ramen planos', 'fideos', 5.00, 20, 'Fideos ramen planos, perfectos para sopas con un toque diferente.', 'fideos1'),
        ('fideos de ramen', 'fideos', 4.65, 35, 'Fideos ramen clásicos, perfectos para cualquier receta japonesa.', 'fideos2'),
        ('fideos de udon', 'fideos', 4.99, 43, 'Fideos udon gruesos, perfectos para guisos o sopas.', 'fideos3'),
        ('fideos de arroz', 'fideos', 4.30, 19, 'Fideos de arroz ligeros, perfectos para salteados.', 'fideos4'),
        ('fideos de yakisoba', 'fideos', 5.10, 27, 'Fideos yakisoba ideales para salteados con verduras y carne.', 'fideos5'),
        ('fideos integrales', 'fideos', 6.30, 36, 'Fideos integrales para opciones más saludables.', 'fideos6'),
        
        -- Frutas y verduras
        
        ('setas shitakes', 'fruta y verduras', 6.30, 36, 'Hongos shitake frescos, ideales para guisos y sopas.', 'verdura1'),
        ('tofu blanco', 'fruta y verduras', 5.80, 15, 'Tofu blanco suave, ideal para recetas veganas.', 'verdura2'),
        ('jengibre', 'fruta y verduras', 5.10, 13, 'Jengibre fresco, imprescindible en la cocina asiática.', 'verdura3'),
        ('col china', 'fruta y verduras', 3.10, 21, 'Repollo chino fresco, perfecto para salteados y sopas.', 'verdura4'),
        ('lychee', 'fruta y verduras', 9.99, 36, 'Dulce fruta exótica, ideal para postres o como merienda.', 'verdura5'),
        ('alga wakame', 'fruta y verduras', 5.90, 14, 'Alga wakame deshidratada, ideal para sopas y ensaladas.', 'verdura6'),
        
        -- Comidas preparadas
        
        ('pollo con curry', 'platos', 6.90, 8, 'Pollo al curry picante, perfecto para acompañar con arroz.', 'plato1'),
        ('variado de sushi', 'platos', 15.50, 10, 'Variedad de sushi fresco, perfecto para compartir.', 'plato2'),
        ('ramen de miso', 'platos', 6.50, 9, 'Ramen con caldo de miso, un clásico japonés.', 'plato3'),
        ('pad thai', 'platos', 7.00, 14, 'Pad Thai con salsa tradicional, una explosión de sabores.', 'plato4'),
        ('rollitos de primavera', 'platos', 3.50, 12, 'Rollitos de primavera crujientes, perfectos para picar.', 'plato5'),
        ('pato pekin', 'platos', 10.50, 5, 'Pato pekinés, tierno y jugoso.', 'plato6'),
        
        -- Utensilios
        
        ('palillos de madera', 'utensilios', 3.00, 20, 'Palillos de madera reutilizables, ligeros y prácticos.', 'utensilio1'),
        ('palillos de metal', 'utensilios', 3.99, 13, 'Palillos de metal duraderos, ideales para comidas asiáticas.', 'utensilio2'),
        ('bol pequeño', 'utensilios', 4.20, 36, 'Cuenco pequeño para postres o meriendas.', 'utensilio3'),
        ('bol de ramen', 'utensilios', 7.00, 41, 'Cuenco grande perfecto para servir ramen.', 'utensilio4'),
        ('plato rectangular', 'utensilios', 5.60, 25, 'Plato rectangular ideal para sushi o aperitivos.', 'utensilio5'),
        ('makisu', 'utensilios', 2.80, 15, 'Makisu para preparar sushi fácilmente.', 'utensilio6');
    """.trimIndent()
            db.execSQL(insertStatement)
        }


        // Inserta los usuarios iliciales
        private fun insertInitialUsuarios(db: SQLiteDatabase) {
            val insertStatement = """
        INSERT INTO $TABLE_USUARIOS ($COLUMN_NOMBRE, $COLUMN_EMAIL, $COLUMN_TELEFONO ,  $COLUMN_CONTRASENA ) VALUES
        ('Juan Pérez', 'juan.perez@mail.com', '123456789', '1234'),
        ('Ana Gómez', 'ana.gomez@mail.com', '987654321', '1234'),
        ('Testeo', 'a', '000000000', 'a'),
        ('ADMIN', 'admin', '111111111', 'admin');

        """.trimIndent()
            db.execSQL(insertStatement)
        }

        fun deleteProducto(nombre: String): Boolean {
            val db = this.writableDatabase
            val selection = "$COLUMN_NOMBRE = ?"  // Asegura que se use la la columna 'nombre'
            val selectionArgs = arrayOf(nombre)

            // Intentamos eliminar el producto con el nombre especificado de la tabla de productos
            val rowsDeleted = db.delete(TABLE_PRODUCTOS, selection, selectionArgs)

            return rowsDeleted > 0  // Si se eliminaron filas, el producto existía y fue eliminado
        }


        // Metodo para obtener información del producto a través de un SELECT
        fun getProductInfo(): MutableList<Producto> {
            val db = readableDatabase
            val selectQuery = " SELECT * FROM $TABLE_PRODUCTOS"
            val productos = mutableListOf<Producto>()
            val obtainQuery = db.rawQuery(selectQuery, null)
            while (obtainQuery.moveToNext()) {
                productos.add(
                    Producto(
                        obtainQuery.getInt(0),
                        obtainQuery.getString(1),
                        obtainQuery.getString(2),
                        obtainQuery.getDouble(3),
                        obtainQuery.getInt(4),
                        obtainQuery.getString(5),
                        obtainQuery.getString(6)
                    )
                )
            }
            obtainQuery.close() // Cerrar el cursor después de usarlo
            return productos
        }

        // Metodo para obtener un producto por su ID
        fun getProductById(productId: Int): Producto? {
            val db = readableDatabase
            val selectQuery = "SELECT * FROM $TABLE_PRODUCTOS WHERE id = ?"
            var producto: Producto? = null
            val cursor = db.rawQuery(selectQuery, arrayOf(productId.toString()))

            if (cursor.moveToFirst()) {
                producto = Producto(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getString(6)
                )
            }
            cursor.close() // Cerrar el cursor
            return producto
        }
        fun getProductInfoCategoria(categoria: String): MutableList<Producto> {
            val db = readableDatabase
            val selectQuery = " SELECT * FROM $TABLE_PRODUCTOS WHERE $COLUMN_CATEGORIA = '$categoria'"
            val productos = mutableListOf<Producto>()
            val obtainQuery = db.rawQuery(selectQuery, null)
            while (obtainQuery.moveToNext()) {
                productos.add(
                    Producto(
                        obtainQuery.getInt(0),
                        obtainQuery.getString(1),
                        obtainQuery.getString(2),
                        obtainQuery.getDouble(3),
                        obtainQuery.getInt(4),
                        obtainQuery.getString(5),
                        obtainQuery.getString(6)
                    )
                )
            }
            obtainQuery.close() // Cerrar el cursor después de usarlo
            return productos
        }

        fun searchProductByNameLike(name: String): List<Producto> {
            val db = this.readableDatabase
            val query = "SELECT * FROM productos WHERE nombre LIKE ?"

            // Usamos '%' para hacer una búsqueda parcial (como un 'contains' en otros lenguajes)
            val cursor = db.rawQuery(query, arrayOf("%$name%"))
            val productos = mutableListOf<Producto>()

            while (cursor.moveToNext()) {
                productos.add(
                    Producto(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getString(6)
                    )
                )
            }
            cursor.close() // Cerrar el cursor después de usarlo
            return productos
        }

        fun openDatabase(context: Context): SQLiteDatabase {
            val dbHelper = DBHelper(context)
            return dbHelper.writableDatabase
        }

        // Metodo para añadir un producto
        fun addProducto(
            context: Context,
            nombre: String,
            categoria: String,
            precio: Double,
            stock: Int,
            descripcion: String,
            imagen: String
        ): Boolean {
            if (nombre.isBlank() || categoria.isBlank() || precio <= 0 || stock <= 0 || descripcion.isBlank() || imagen.isBlank()) {
                throw IllegalArgumentException("Todos los campos son obligatorios y deben ser válidos.")
            }

            val db = openDatabase(context)
            val contentValues = ContentValues().apply {
                put(COLUMN_NOMBRE, nombre)
                put(COLUMN_CATEGORIA, categoria)
                put(COLUMN_PRECIO, precio)
                put(COLUMN_STOCK, stock)
                put(COLUMN_DESCRIPCION, descripcion)
                put(COLUMN_IMAGEN, imagen)
            }

            val result = db.insert(TABLE_PRODUCTOS, null, contentValues)
            db.close()
            return result != -1L
        }


        // Constantes de la base de datos
        companion object {
            const val DATABASE_NAME = "productosDB"
            const val DATABASE_VERSION = 1

            // Tabla PORDUCTOS
            const val TABLE_PRODUCTOS = "productos"
            const val COLUMN_ID = "id"
            const val COLUMN_NOMBRE = "nombre"
            const val COLUMN_CATEGORIA = "categoria"
            const val COLUMN_PRECIO = "precio"
            const val COLUMN_STOCK = "stock"
            const val COLUMN_DESCRIPCION = "descripcion"
            const val COLUMN_IMAGEN = "imagen"

            // Tabla USUARIOS
            const val TABLE_USUARIOS = "usuarios"
            const val COLUMN_EMAIL = "email"
            const val COLUMN_TELEFONO = "telefono"
            const val COLUMN_CONTRASENA = "contrasena"


            // Tabla PEDIDOS
            const val TABLE_PEDIDOS = "pedidos"
            const val COLUMN_CLIENTE_ID = "cliente_id"
            const val COLUMN_FECHA = "fecha"
            const val COLUMN_TOTAL = "total"

            const val a = "prueba"

        }

        //Base de datos actulizada por Niko 15/01/2025
    }
}
