package nl.acme.kotlindemo.utils

class Assert {
    companion object {
        fun assertEquals(expected: Any, actual: Any) {
            assertTrue(expected == actual, "Expected: $expected, Actual: $actual")
            assertOK("$actual (expected: $expected => OK)")
        }
        fun assertNotEquals(notExpected: Any, actual: Any) {
            assertFalse(notExpected == actual, "Not expected: $notExpected, Actual: $actual")
            assertOK("$actual (not expected: $notExpected => OK)")
        }
        fun assertFalse(booleanExpr: Boolean, message: String = "Expected $booleanExpr to be false") {
            if (booleanExpr) {
                throw AssertionError(message)
            }
        }
        fun assertTrue(booleanExpr: Boolean, message: String = "Expected $booleanExpr to be true") {
            if (!booleanExpr) {
                throw AssertionError(message)
            }
            assertOK()
        }
        fun assertOK(message: String = "OK") {
            println(message)
        }
    }
}