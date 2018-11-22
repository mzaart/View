# Package core.views.propertyDelegates

This package contains property delegates for View properties.

Typically, view property delegates serve two purposes, the first is
to notify the [ViewTreeRenderer][core.renderers.ViewTreeRenderer] about
any change in the property's value while the second purpose is to
ensure using [Validators][utils.validators] that the value of the
property meets certain conditions.
