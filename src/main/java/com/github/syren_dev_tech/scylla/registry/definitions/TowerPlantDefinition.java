package com.github.syren_dev_tech.scylla.registry.definitions;

public class TowerPlantDefinition {

    public final String name;
    public final BlockDefinition<?> body;
    public final BlockDefinition<?> head;

    public TowerPlantDefinition(String name, BlockDefinition<?> body, BlockDefinition<?> head) {
        this.name = name;
        this.body = body;
        this.head = head;
    }
}
