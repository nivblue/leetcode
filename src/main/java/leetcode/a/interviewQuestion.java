package leetcode.a;

/**
 * resourceMap = {
 *     "prod-eu": { "a123", "b456" },
 *     "prod-us": { "g21" },
 *     "dev" : {"c124", "d32", "vm-1" }
 * }
 *
 * function input:
 * List<Events> = [
 *  { eventId : "123", name: "a123", action: "read", actor: "abcd"}
 *  { eventId : "1145", name: "g21", action: "write", actor: "abcef"}
 *  { eventId : "9190", name: "a123", action: "write", actor: "abcd"}
 *  { eventId : "8585", name: "b456", action: "read", actor: "abcd"}
 * ]
 *
 *  function:
 *  List<Object> groupEventsByResource(events);
 *
 * expected:
 * [
 *  {
 *      "groupId": "prod-eu",
 *      "resources": [ "a123", "b456" ],
 *      "events": [
 *        { eventId : "123", name: "a123", action: "read", actor: "abcd"},
 *        { eventId : "9190", name: "a123", action: "write", actor: "abcd"}
 *        { eventId : "8585", name: "b456", action: "read", actor: "abcd"}
 *      ]
 *  },
 *  {
 *       "groupId": "prod-us",
 *       "resources": [ "g21"],
 *       "events": [
 *         { eventId : "1145", name: "g21", action: "write", actor: "abcef"}
 *       ]
 *   }
 * ]
 *
 *
 * // for event : events
 * //    event.name -> for (List) resources : resourceGroup -> for resource : resources -> resource == event.name
 *
 * */
public class interviewQuestion {
}
