package utils.validators

sealed class Condition<T>(val validate: (T) -> Boolean)


abstract class IntegerCondition(validate: (Int) -> Boolean): Condition<Int>(validate)
class IZero: IntegerCondition({x -> x == 0})
class IPositive: IntegerCondition({x -> x > 0})
class INegative: IntegerCondition({x -> x < 0})
class INonNegative: IntegerCondition({x -> x >= 0})
class INonPositive: IntegerCondition({x -> x <= 0})

abstract class DoubleCondition(validate: (Double) -> Boolean): Condition<Double>(validate)
class DZero: DoubleCondition({x -> x == 0.0})
class DPositive: DoubleCondition({x -> x > 0})
class DNegative: DoubleCondition({x -> x < 0})
class DNonNegative: DoubleCondition({x -> x >= 0})
class DNonPositive: DoubleCondition({x -> x <= 0})

abstract class StringCondition(validate: (String) -> Boolean): Condition<String>(validate)
class Empty: StringCondition({s -> s.isEmpty()})
class WhiteSpace: StringCondition({s -> s.isBlank()})
class NonEmpty: StringCondition({s -> !s.isEmpty()})
class Present: StringCondition({s ->  !Empty().validate(s) && !WhiteSpace().validate(s)})