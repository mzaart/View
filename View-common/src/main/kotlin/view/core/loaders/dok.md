# Package view.core.loaders

This packages contains classes for loading view trees
from a serialized format.

The key components of this package are:

* Ids Class: Keeps track of ids of loaded views and
ensures no views have duplicate ids

* treeLoaders Package: Is responsible for building the view tree.

    *  One important component of this package is the [Node][view.core.loaders.viewTree.nodes], which is an abstraction of
    a serialized view.

    * Regardless of the serialization format (JSON, XML, YAML, etc...),
    the view tree will be serialized into a tree-like structure of key-value pairs that
    represent a view. The Node objects are responsible for parsing these key-value pairs.
    
    * The view tree builders are oblivious to the serialization format used, they just
    traverse Node objects.

* builders Package: Responsible for building views from key-value pairs that are parsed
by the Node objects.

    * View Builders are map-based classes. Please refer to the package
    [view.utils.mapBased.keys] for more information.
