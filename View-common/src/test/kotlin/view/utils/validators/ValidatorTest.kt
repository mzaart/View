package view.utils.validators

import view.utils.validators.conditions.Condition
import kotlin.test.Test
import kotlin.test.assertFailsWith

class ValidatorTest {

    enum class MockConditions: Condition<Int> {
        PASS {
            override fun isValid(value: Int) = true
        },

        FAIL {
            override fun isValid(value: Int) = false
        }
    }

    @Test
    fun unanimousPass() {
        Validator(Validator.Strategy.UNANIMOUS, MockConditions.PASS).validate(1)
    }

    @Test
    fun unanimousPassEmpty() {
        Validator<Int>(Validator.Strategy.UNANIMOUS).validate(1)
    }
    
    @Test
    fun unanimousFail() {
        assertFailsWith<ValidationException> {
            Validator(
                    Validator.Strategy.UNANIMOUS,
                    MockConditions.PASS,
                    MockConditions.PASS,
                    MockConditions.FAIL
            ).validate(1)
        }
    }

    @Test
    fun affirmativePass() {
        Validator(
                Validator.Strategy.AFFIRMATIVE,
                MockConditions.FAIL,
                MockConditions.PASS,
                MockConditions.FAIL
        ).validate(1)
    }

    @Test
    fun affirmativePassEmpty() {
        Validator<Int>(Validator.Strategy.AFFIRMATIVE).validate(1)
    }

    @Test
    fun affirmativeFail() {
        assertFailsWith<ValidationException> {
            Validator(
                    Validator.Strategy.AFFIRMATIVE,
                    MockConditions.FAIL,
                    MockConditions.FAIL,
                    MockConditions.FAIL
            ).validate(1)
        }
    }

    @Test
    fun consensusPassGreaterThan() {
        Validator(
                Validator.Strategy.CONSENSUS,
                MockConditions.PASS,
                MockConditions.PASS,
                MockConditions.FAIL
        ).validate(1)
    }

    @Test
    fun consensusPassEquall() {
        Validator(
                Validator.Strategy.CONSENSUS,
                MockConditions.FAIL,
                MockConditions.PASS,
                MockConditions.FAIL,
                MockConditions.PASS
        ).validate(1)
    }

    @Test
    fun consensusPassEmpty() {
        Validator<Int>(Validator.Strategy.CONSENSUS).validate(1)
    }

    @Test
    fun consensusFail() {
        assertFailsWith<ValidationException> {
            Validator(
                    Validator.Strategy.CONSENSUS,
                    MockConditions.FAIL,
                    MockConditions.PASS,
                    MockConditions.FAIL,
                    MockConditions.PASS,
                    MockConditions.FAIL
            ).validate(1)
        }
    }
}