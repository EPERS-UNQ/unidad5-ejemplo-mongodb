package ar.edu.unq.epers.unidad5

import ar.edu.unq.epers.unidad5.dao.ProductoDAO
import ar.edu.unq.epers.unidad5.model.Producto
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TransactionsTest {
    lateinit var dao: ProductoDAO

    @Before
    fun setup() {
        dao = ProductoDAO()
    }

    @After
    fun dropAll() {
        dao.deleteAll()
    }

    @Test
    fun testRollback() {
        var productos = dao.getByMarca("Terrabusi")
        Assert.assertTrue(productos.isEmpty())

        val terrabusi = Producto("11101", "Alfajor", "Terrabusi")

        dao.startTransaction()
        dao.save(terrabusi)
        productos = dao.getByMarca("Terrabusi")
        Assert.assertEquals(1, productos.size.toLong())

        dao.rollack()

        productos = dao.getByMarca("Terrabusi")
        Assert.assertTrue(productos.isEmpty())
    }

    @Test
    fun testCommit() {
        dao = ProductoDAO()
        var productos = dao.getByMarca("Terrabusi")
        Assert.assertEquals(0, productos!!.size.toLong())
        val terrabusi = Producto("11101", "Alfajor", "Terrabusi")

        dao.startTransaction()
        dao.save(terrabusi)
        dao.commit()

        productos = dao.getByMarca("Terrabusi")

        Assert.assertEquals(1, productos!!.size.toLong())
    }

}