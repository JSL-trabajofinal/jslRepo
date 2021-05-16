/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package domainapp.modules.impl.visits.dom;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import com.google.common.collect.ComparisonChain;

import org.joda.time.LocalDateTime;

import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;

import domainapp.modules.impl.reclamos.dom.Reclamo;
import lombok.Getter;
import lombok.Setter;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "visits")
@javax.jdo.annotations.DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.DATE_TIME, column = "version")
@javax.jdo.annotations.Unique(name = "Visit_visitAt_reclamo_UNQ", members = {"visitAt", "reclamo"})
@javax.jdo.annotations.Index(name = "Visit_reclamo_visitAt_IDX", members = {"reclamo", "visitAt"})
@DomainObject(auditing = Auditing.ENABLED)
@DomainObjectLayout()  // causes UI events to be triggered
public class Visit implements Comparable<Visit> {

    public Visit(final Reclamo reclamo, final LocalDateTime visitAt, final String reason) {
        this.reclamo = reclamo;
        this.visitAt = visitAt;
        this.reason = reason;
    }

    public String title() {
        return String.format(
                "%s: %s (%s)",
                getVisitAt().toString("yyyy-MM-dd hh:mm"),
                getReclamo().getUsuario().getName(),
                getReclamo().getName());
    }

    @javax.jdo.annotations.Column(allowsNull = "false", name = "reclamoId")
    @Property(editing = Editing.DISABLED)
    @Getter
    @Setter
    private Reclamo reclamo;

    @javax.jdo.annotations.Column(allowsNull = "false")
    @Property(editing = Editing.DISABLED)
    @Getter
    @Setter
    private LocalDateTime visitAt;

    @javax.jdo.annotations.Column(allowsNull = "false", length = 4000)
    @Property(editing = Editing.ENABLED)
    @PropertyLayout(multiLine = 5)
    @Getter
    @Setter
    private String reason;

    @Override
    public String toString() {
        return getVisitAt().toString("yyyy-MM-dd hh:mm");
    }

    @Override
    public int compareTo(final Visit otro) {
        return ComparisonChain.start()
                .compare(this.getVisitAt(), otro.getVisitAt())
                .compare(this.getReclamo(), otro.getReclamo())
                .result();
    }
}
