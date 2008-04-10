package org.easyb.plugin

class SpecificationResultsBuilder extends BuilderSupport {
    protected void setParent(Object parent, Object child) {
        if (parent instanceof SpecificationResults && child instanceof SpecResult)
            parent.addResult(child)
        else
            throw new RuntimeException('Unexcepted types encountered')
    }

    protected Object createNode(Object name) {
        return createNode(name, null, null)
    }

    protected Object createNode(Object name, Object value) {
        return createNode(name, null, value)
    }

    protected Object createNode(Object name, Map attributes) {
        return createNode(name, attributes, null)
    }

    protected Object createNode(Object name, Map attributes, Object value) {
        if (name == 'specificationResults')
            return new SpecificationResults()
        if (name == 'passingSpec')
            return new SpecResult(value.toString())
    }
}