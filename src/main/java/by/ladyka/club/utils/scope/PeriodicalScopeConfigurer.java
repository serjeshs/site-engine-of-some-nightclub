package by.ladyka.club.utils.scope;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.data.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class PeriodicalScopeConfigurer implements Scope {

	private Map<String, Pair<Long, Object>> map = new HashMap<>();

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		Long currentTimeMillis = System.currentTimeMillis();
		if (map.containsKey(name)) {
			Pair<Long, Object> pair = map.get(name);
			long secondsSinceLastRequest = currentTimeMillis - pair.getFirst();
			//check if interval is exceeded?
			if (secondsSinceLastRequest > 60 * 60 * 1000) {
				//if exceeded, then create new pair and take bean again (color will be changed randomly)
				map.put(name, Pair.of(currentTimeMillis, objectFactory.getObject()));
			}
		} else {
			//put if not existed
			map.put(name, Pair.of(currentTimeMillis, objectFactory.getObject()));
		}
		return map.get(name).getSecond();
	}

	@Override
	public Object remove(String name) {
		return map.remove(name);
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {

	}

	@Override
	public Object resolveContextualObject(String key) {
		return null;
	}

	@Override
	public String getConversationId() {
		return null;
	}
}
