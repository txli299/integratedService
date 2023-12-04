package com.gomobile.integratedService.repo;

import com.gomobile.integratedService.model.*;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CafeRepository extends MongoRepository<Cafe,String> {
    Optional<Cafe> findById(String id);

    @Aggregation(pipeline = {
            "{ $match: { 'name': ?0 } }",
            "{ $unwind: '$machines' }", // Deconstructs the machines array
            "{ $group: { " +
                    "  _id: {  'type': '$machines.type'}, " +
                    "  total: { $sum: 1 }, " + // Counts all machines
                    "  activeCount: { $sum: { $cond: ['$machines.activated', 1, 0] } } " + // Counts only active machines
                    "} }",
            "{ $project: { " +
                    "  'type': '$_id.type', " +
                    "  'total': '$total', " +
                    "  'activeCount': '$activeCount',"+
                    "} }"
    })
    List<MachineInfo> countActiveMachines(String cafeName);

    @Aggregation(pipeline = {
            "{$match:  {'_id': ?0}}",
            "{$unwind:  '$machines'}",
            "{$match:  {'machines.type': ?1}}",
            "{ $group: { " +
                    "  _id: '$machines.activated', " + // Group by the activated status
                    "  machines: { $push: '$machines' } " + // Collect all machines in each group
                    "} }",
            "{ $project: { " +
                    "  type: ?1," +
                    "  activated: { $cond: { if: { $eq: ['$_id', true] }, then: '$machines', else: [] } },  " +
                    "  notActivated: { $cond: { if: { $eq: ['$_id', false] }, then: '$machines', else: [] } }  " +
                    "} }"

    })
    MachineDto getDetailMachine(String name,String type);
}
