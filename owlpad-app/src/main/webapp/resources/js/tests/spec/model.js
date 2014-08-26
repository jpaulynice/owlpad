define(['backbone'], function(Backbone) {
  describe("Todo Model", function() {
    it("should have a default empty string title", function() {
      var t = new Backbone.Model({title:''});
      expect(t.get('title')).toBe("");
    });
  });
});