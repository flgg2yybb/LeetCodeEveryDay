package main

import (
	"fmt"
	"sort"
)

func main() {
	arr := []int{5, 6, 3, 5, 7, 4}
	sort.Slice(arr, func(i, j int) bool { return arr[i] < arr[j] })
	println(fmt.Sprintf("%+v", arr))
}
