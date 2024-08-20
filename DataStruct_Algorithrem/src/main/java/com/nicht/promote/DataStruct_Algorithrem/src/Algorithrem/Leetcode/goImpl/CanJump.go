package goImpl

import "math"

func CanJump(nums []int) bool {
	max := 0
	for i, num := range nums {
		if max < i {
			return false
		}
		max = int(math.Max(float64(max), float64(i+num)))
	}
	return true
}

func CanJumpMinStep(nums []int) int {
	end := 0
	step := 0
	maxPosition := 0
	arrLen := len(nums)
	for i := 0; i < arrLen-1; i++ {
		// 在当前可跳跃范围内 记录下一个可以跳跃的最大距离位置
		maxPosition = int(math.Max(float64(maxPosition), float64(i+nums[i])))
		if i == end {
			end = maxPosition
			step++
		}
	}
	return step
}

func minDistance(word1 string, word2 string) int {

	return 0
}
