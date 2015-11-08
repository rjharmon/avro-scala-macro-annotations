package com.miguno.avro

import test.TestUtil

import org.specs2.mutable.Specification

import com.julianpeeters.avro.annotations._

@AvroTypeProvider("tests/src/test/resources/AvroTypeProviderTestSchemaUnion.avsc")
@AvroRecord
case class User()

@AvroTypeProvider("tests/src/test/resources/AvroTypeProviderTestSchemaUnion.avsc")
@AvroRecord
case class PictureSize()

@AvroTypeProvider("tests/src/test/resources/AvroTypeProviderTestSchemaUnion.avsc")
@AvroRecord
case class Tweet()

class AvroTypeProviderSchemaUnionTest extends Specification {

  "Case classes generated from an .avsc file containing a union of schemas" should {
    
    "serialize and deserialize types referencing other items in the union" in {
      val record = Tweet("This is Hector. The fool who thought he killed Achilles.",
        user = User("Achilles", 1), user_mentions = List(User("Hector", 2), User("Achilles", 1)))
      TestUtil.verifyWriteAndRead(record)
    }

    "serialize and deserialize types which aren't referenced by any other item in the union" in {
      val size = PictureSize(300.0, 172.0)
      TestUtil.verifyWriteAndRead(size)
    }
  }

}
