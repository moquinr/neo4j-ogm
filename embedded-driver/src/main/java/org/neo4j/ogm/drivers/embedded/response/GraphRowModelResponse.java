/*
 * Copyright (c) 2002-2022 "Neo4j,"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of Neo4j.
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
package org.neo4j.ogm.drivers.embedded.response;

import org.neo4j.graphdb.Result;
import org.neo4j.ogm.drivers.embedded.driver.EmbeddedEntityAdapter;
import org.neo4j.ogm.model.GraphRowListModel;
import org.neo4j.ogm.response.model.DefaultGraphRowListModel;
import org.neo4j.ogm.result.adapter.GraphRowModelAdapter;

/**
 * @author Vince Bickers
 * @author Michael J. Simons
 */
public class GraphRowModelResponse extends EmbeddedResponse<GraphRowListModel> {

    private final GraphRowModelAdapter adapter;

    public GraphRowModelResponse(Result result, EmbeddedEntityAdapter entityAdapter) {

        super(result);

        this.adapter = new GraphRowModelAdapter(new EmbeddedGraphModelAdapter(entityAdapter));
        this.adapter.setColumns(result.columns());
    }

    @Override
    public GraphRowListModel next() {

        if (result.hasNext()) {
            DefaultGraphRowListModel model = new DefaultGraphRowListModel();

            while (result.hasNext()) {
                model.add(adapter.adapt(result.next()));
            }
            return model;
        }
        return null;
    }
}
