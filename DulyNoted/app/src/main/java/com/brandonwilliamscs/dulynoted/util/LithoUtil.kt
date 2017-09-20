package com.brandonwilliamscs.dulynoted.util

import com.facebook.litho.Component
import com.facebook.litho.ComponentLayout
import com.facebook.litho.ComponentLifecycle

/**
 * Created by Brandon on 9/20/2017.
 */

// "JvmName" is a way to differentiate these four extension methods within the JVM without actually renaming them here.
@JvmName("childrenSeqComponentLayout")
fun ComponentLayout.ContainerBuilder.children(children: Sequence<ComponentLayout>): ComponentLayout.ContainerBuilder
    = children.fold(this) { builder, child -> builder.child(child) }
@JvmName("childrenSeqComponentLayoutBuilder")
fun ComponentLayout.ContainerBuilder.children(childBuilders: Sequence<ComponentLayout.Builder>): ComponentLayout.ContainerBuilder
    = childBuilders.fold(this) { builder, child -> builder.child(child) }
@JvmName("childrenSeqComponent")
fun ComponentLayout.ContainerBuilder.children(components: Sequence<Component<*>>): ComponentLayout.ContainerBuilder
    = components.fold(this) { builder, child -> builder.child(child) }
@JvmName("childrenSeqComponentBuilder")
fun ComponentLayout.ContainerBuilder.children(componentBuilders: Sequence<Component.Builder<*, *>>): ComponentLayout.ContainerBuilder
    = componentBuilders.fold(this) { builder, child -> builder.child(child) }
