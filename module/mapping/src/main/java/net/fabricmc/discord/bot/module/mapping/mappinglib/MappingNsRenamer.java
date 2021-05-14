/*
 * Copyright (c) 2021 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.discord.bot.module.mapping.mappinglib;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class MappingNsRenamer extends ForwardingMappingVisitor {
	public MappingNsRenamer(MappingVisitor next, Map<String, String> nameMap) {
		super(next);

		Objects.requireNonNull(nameMap, "null name map");

		this.nameMap = nameMap;
	}

	@Override
	public void visitNamespaces(String srcNamespace, List<String> dstNamespaces) {
		String newSrcNamespace = nameMap.getOrDefault(srcNamespace, srcNamespace);
		List<String> newDstNamespaces = new ArrayList<>(dstNamespaces.size());

		for (String ns : dstNamespaces) {
			newDstNamespaces.add(nameMap.getOrDefault(ns, ns));
		}

		super.visitNamespaces(newSrcNamespace, newDstNamespaces);
	}

	private final Map<String, String> nameMap;
}
