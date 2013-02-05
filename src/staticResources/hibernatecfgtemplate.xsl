<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml"
		doctype-system="http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd" />
	<xsl:template match="/">
		<hibernate-configuration>
			<session-factory>
				<property name="hibernate.hbm2ddl.auto">update</property>
				<property name="hibernate.dialect">
					org.hibernate.dialect.MySQLDialect
				</property>
				<property name="log4j.logger.org.hibernate.type">
					TRACE
				</property>
				<property name="hibernate.max_fetch_depth">3</property>
				<property name="hibernate.connection.driver_class">
					com.mysql.jdbc.Driver
				</property>
				<property name="hibernate.ejb.event.post-insert">
					org.hibernate.ejb.event.EJB3PostInsertEventListener,org.hibernate.envers.event.AuditEventListener
				</property>
				<property name="hibernate.show_sql">true</property>
				<property name="hibernate.ejb.event.post-update">
					org.hibernate.ejb.event.EJB3PostUpdateEventListener,org.hibernate.envers.event.AuditEventListener
				</property>
				<property name="hibernate.ejb.event.post-delete">
					org.hibernate.ejb.event.EJB3PostDeleteEventListener,org.hibernate.envers.event.AuditEventListener
				</property>
				<property name="hibernate.ejb.event.pre-collection-update">
					org.hibernate.envers.event.AuditEventListener
				</property>
				<property name="hibernate.ejb.event.pre-collection-remove">
					org.hibernate.envers.event.AuditEventListener
				</property>
				<property name="hibernate.ejb.event.post-collection-recreate">
					org.hibernate.envers.event.AuditEventListener
				</property>
				<property name="org.hibernate.envers.audit_table_suffix">_VER</property>
				<property name="org.hibernate.envers.revision_field_name">REV</property>
				<property name="org.hibernate.envers.revision_type_field_name">REVTYPE</property>
				<property name="org.hibernate.envers.default_schema">
					<xsl:value-of select="database" />
				</property>
				<property name="org.hibernate.envers.revision_on_collection_change">false</property>

				<!-- Assume test is the database name -->
				<property name="hibernate.connection.url">
					jdbc:mysql://localhost/<xsl:value-of select="database" />
				</property>
				<property name="hibernate.connection.username">
					root
				</property>
				   <property name="hibernate.connection.password">root 
				 </property>

				<!-- Test property -->
				<property name="javax.persistence.validation.mode">none</property>
				<!-- Test property -->

				<!-- List of XML mapping files -->
				<mapping resource="/hibernateEntities/User.hbm.xml" />
				<mapping resource="/hibernateEntities/FormFields.hbm.xml" />
				<mapping resource="/hibernateEntities/MailingGroup.hbm.xml" />
				<mapping resource="/hibernateEntities/MailingList.hbm.xml" />
				<mapping resource="/hibernateEntities/Event.hbm.xml" />
				<mapping resource="/hibernateEntities/FilledForm.hbm.xml" />
				<mapping resource="/hibernateEntities/FormType.hbm.xml" />
				<mapping resource="/hibernateEntities/FieldType.hbm.xml" />
				<mapping resource="/hibernateEntities/FieldSelection.hbm.xml" />
				<mapping resource="/hibernateEntities/Form.hbm.xml" />
				<mapping resource="/hibernateEntities/FormPermissions.hbm.xml" />
				<mapping resource="/hibernateEntities/UserType.hbm.xml" />


			</session-factory>

		</hibernate-configuration>
	</xsl:template>
</xsl:stylesheet>

