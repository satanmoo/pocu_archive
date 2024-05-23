cmake_minimum_required(VERSION 3.28)
project(no_buffer_overflow C)

set(CMAKE_C_STANDARD 11)

add_executable(no_buffer_overflow
        main.c)
