cmake_minimum_required(VERSION 3.5)
project(min_max)

# Set the C standard to C99

set(CMAKE_C_STANDARD 99)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -W -pedantic-errors")

# Define the source files
add_executable(min_max main.c my_algorithm.c)