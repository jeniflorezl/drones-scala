package co.com.s4n.drones.dominio.servicios

import co.com.s4n.drones.dominio.vo.{A, D, I}
import org.scalatest.FunSuite
import org.scalatest.Matchers._

import scala.collection.immutable.{IndexedSeq, Seq}
import scala.language.postfixOps
import scala.util.{Failure, Success}
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class ServicioRutaTest extends FunSuite{

  test("test preparar rutas"){
    val entregas = OperandoArchivos.leerArchivo
    if (entregas.isSuccess){
      val rutas = ServicioRutaObj.prepararRutas(entregas.get)
      rutas should be(List(List(A(), A(), A(), A(), I(), A(), A(),
        D()), List(D(), D(), A(), I(), A(), D()), List(A(), A(), I(), A(), D(), A(), D())))
    }else "Error"

  }

}
