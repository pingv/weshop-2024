# Liquibase Changelog Documentation

## Overview
This document provides information about the Liquibase changelog structure and conventions used in this project.

## Directory Structure
- `db.changelog-master.xml`: The master changelog file that includes all individual changelog files.
- `releases/`: Directory containing release-specific changelog files.
  - 0.9.0/ contains the changelog files for all past releases before introduction of Liquibase.

## Changelog Files
- `001-create-tables.xml`: Creates initial tables.
- `002-insert-data.xml`: Inserts initial data into tables.
- `003-create-sequence.xml`: Creates sequences.
- `004-add-email-column.xml`: Adds the email column to the customer table if it does not exist.
- `alter-customer-table-add-phone.xml`: Adds the phone column to the customer table if it does not exist.

## Conventions
- Each changelog file should have a unique `id` and `author`.
- Use preconditions to ensure changes are only applied when necessary.
- Use SQL scripts for complex changes that are easier to express in SQL.

## Important Decisions
- We chose to use XML format for most changelogs for better validation and metadata support.
- SQL scripts are used for certain changes for conciseness and familiarity.

## How to Add a New Changelog
1. Create a new changelog file in the `releases/` directory.
2. Define the changeset with a unique `id` and `author`.
3. Include the new changelog file in `db.changelog-master.xml`.

## Example
Here is an example of a new changelog file:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
    xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
        http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.20.xsd">

    <changeSet id="005" author="vishnu">
        <createTable tableName="products">
            <column name="id" type="BIGINT" autoIncrement="true">
                <constraints primaryKey="true" nullable="false"/>
            </column>
            <column name="name" type="VARCHAR(255)">
                <constraints nullable="false"/>
            </column>
            <column name="description" type="TEXT"/>
            <column name="price" type="DECIMAL(10,2)">
                <constraints nullable="false"/>
            </column>
            <column name="created_at" type="TIMESTAMP">
                <constraints nullable="false"/>
            </column>
            <column name="updated_at" type="TIMESTAMP">
                <constraints nullable="false"/>
            </column>
        </createTable>
    </changeSet>
</databaseChangeLog>
```

### Using SQL constructs in Liquibase changelogs has both advantages and disadvantages compared to other formats like XML, YAML, or JSON.

TLDR; 
- Use XML for most changelogs
- While SQL scripts are concise and familiar, they may lack the portability, validation, and metadata benefits provided by other formats. 
- XML Examples: src/main/resources/db/changelog/changes/
- SQL Examples: src/main/resources/db/changelog/scripts/

#### Advantages:
1. **Familiarity**: SQL is a widely known language, and many developers are already familiar with it.
2. **Conciseness**: SQL scripts are often more concise and easier to read for simple database changes.
3. **Direct Execution**: SQL scripts can be directly executed by the database, which can be more efficient for certain operations.

#### Disadvantages:
1. **Portability**: SQL scripts can be less portable across different database systems due to variations in SQL dialects.
2. **Validation**: Liquibase provides better validation and error checking for XML, YAML, and JSON formats.
3. **Complexity**: For complex changesets, SQL can become harder to manage and maintain compared to the structured formats.
4. **Metadata**: XML, YAML, and JSON formats allow for richer metadata and better integration with Liquibase features like preconditions and rollback support.
