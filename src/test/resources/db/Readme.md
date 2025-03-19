# Datatype Testing

## Test by Name
All datatypes are tested by their name and its variations, e.g. CHAR, CHARACTER. This is done to ensure that the 
datatypes are recognized properly and to document how the variations are represented (by their name or another 
variant's name). 

## Test with Dimensions
Some datatypes have dimensions like length, size, precision and/or scale. These dimensions are tested to ensure that 
they are recognized properly and to document how they are represented.

### Boundary-value Analysis
For a boundary-value analysis these dimensions are tested with the following values:

* implicit default value (e.g. TIME)
* explicit default value (e.g. TIME(6))
* minimum value (e.g. TIME(0))
* maximum value (e.g. TIME(9))

### Dimension Combinations
For datatypes with multiple dimensions the combinations are tested, e.g. 

* DECIMAL
* DECIMAL(_precisionDefault_)
* DECIMAL(_precisionMin_)
* DECIMAL(_precisionMin_, _scaleDefault_) = DECIMAL(_precisionMin_, _scaleMin_)
* DECIMAL(_precisionMax_)
* DECIMAL(_precisionMax_, _scaleDefault_) = DECIMAL(_precisionMax_, _scaleMin_)
* DECIMAL(_precisionMax_, _scaleMax_)

### Array

An array is a collection of elements.
Even if, according to the SQL standard, these do not have to be exclusively of one data type, most RDBMSs make such a 
restriction. Some RDBMSs allow multidimensional arrays, e.g. PostgreSQL.

Array are also tested with the Boundary-value Analysis for their size(s) in combination with the base datatype of 
the elements and the dimensions of the elements.
