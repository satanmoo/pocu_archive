# Assignment3

## List<T>.AddRange(new <T>[n])

```C#
List<int> ex = new List<int>(5);
ex.AddRange(new int[10]);
```

new int[]로 배열을 생성하면 기본으로 배열의 원소의 값은 0이다.

따라서 List에 `0` 10개를 삽입하게 된다. 삽입하는 과정에서 capacity가 5에서 10으로 늘어났다.

## List<int>[]을 북사해 새로운 List<int>[] 만들기

deep copy

```C#
// 복사할 대상
List<int>[] latest = ....; 

// List<int>의 배열 선언
List<int>[] towers = new List<int>[latest.Length];
// 복사
for (int i = 0; i < latest.Length; i++)
{	
	// List<int>의 배열의 각 원소(List<int>)를 초기화
	towers[i] = new List<int>(latest[i].Capacity);
	towers[i].AddRange(latest[i]);
}
```

