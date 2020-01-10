x <- seq(1,10,.5) 
# x = 1, 1.5, 2, 2.5, 3, 3.5, ..., 10
y <- 1:5 
# y = 1, 2, 3, 4, 5
z <- c(9,6,2,10,-4) 
# z = 9, 6, 2, 10, -4
y + z 
# add two vectors
# result is 1-dimensional vector
z[z<5] 
# all elements in z < 5
mean(z) 
# compute the mean of vector z
zero <- function() { return(0) }
zero()
