/**
 * predicate가 반환하는 string 값을 key로 사용하여 배열의 값을 그룹화합니다
 *
 * @param array 그룹화 할 배열
 * @param predicate key를 반환하는 callback
 */
export function groupBy<T>(
  array: T[],
  predicate: (value: T, index: number, array: T[]) => string,
) {
  return array.reduce((accumulated, value, index, currentArray) => {
    (accumulated[predicate(value, index, currentArray)] ||= []).push(value);
    return accumulated;
  }, {} as {[key: string]: T[]});
}
