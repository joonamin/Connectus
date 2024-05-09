package social.connectus.common.utils;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Slice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SliceResponse<T> {
	private List<T> contents;
	private boolean hasNext;
	private int pageNum;

	public static <E, D> SliceResponse<D> from(Slice<E> entitySlice, Function<E, D> dtoMapper) {
		List<D> list = entitySlice.getContent().stream().map(dtoMapper).toList();
		return new SliceResponse<>(list, entitySlice.hasNext(), entitySlice.getNumber());
	}

	public SliceResponse<T> from(Slice<T> slice) {
		return new SliceResponse<>(slice.getContent(),slice.hasNext(), slice.getNumber());
	}
}

