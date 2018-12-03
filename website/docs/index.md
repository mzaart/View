# View: Write once, use anywhere

View is a high-level and cross-platform UI library written in Kotlin that 
makes UI development easier and faster. View makes your code cleaner, easy
to read and more maintainable.

**Note that this library is still under development. The current release
constitutes a MVP. You can still use it but features may be added,
removed or reimplemented and the library isn't fully tested yet.**

## Why use View?

- Fully Native: With View, you run native code on each platform. The code
that you write gets transpiled to bytecode on Android, Apple Framework on
iOS and Javascript on the web. In addition to that, there is full interoperability
with each platform i.e. you can use your Views in Java, Swift or JS code.

- Abstraction of UI: All what you have to worry about when using View is describing
the structure of your UI. View will take care of the small details such that your
UI adheres to platform specific or your own design standards.

- Easy Customization and Extensibility: View was built with customization in mind.
You can easily customize and extend every component of the library.

- View DSL: You can express your UI structure in an easy to read and type safe
manner using a DSL. By using a DSL you can avoid wasting CPU time parsing XML or
other serialized strings.

- Written in Kotlin: You can already share business logic code between platforms
using Kotlin Mutliplatform. With View you can increase the amount of shared code
by sharing the UI code.


## How View works

The key idea behind View is that the structure of the UI is platform agnostic
while the style is platform specific. Thus, the structure of the UI
should be described in platform independent code.

The library is divided into three main parts:
<br><br>
1) Views: A view represents a particular piece of the UI such as buttons and
text fields. You can think of View classes as "model" classes.
<br><br>
2) View Loaders: View loaders load views from a serialized format such as JSON.
<br><br>
3) View Renderers: This part of the library is platform specific. View renderers
are responsible for mapping views to native objects. For example a view
representing text will be mapped to a `UIView` on iOS, `TextView` on Android and
a `span` element on the web.

View uses Kotlin Multiplatform to share its code across platforms. 
For more information on each component, please see the guides.

