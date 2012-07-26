/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 6/3/12
 * Time: 1:19 PM
 * To change this template use File | Settings | File Templates.
 */


/* [ ---- Gebo Admin Panel - dashboard ---- ] */

$(document).ready(function() {

    //* sortable/searchable list
    //* calendar
    //* responsive table
    //* resize elements on window resize
    var lastWindowHeight = $(window).height();
    var lastWindowWidth = $(window).width();

    //* small gallery grid
    gebo_gal_grid.small();
});



//* gallery grid
gebo_gal_grid = {
    small: function() {
        //* small gallery grid
        $('#small_grid ul').imagesLoaded(function() {
            // Prepare layout options.
            var options = {
                autoResize: true, // This will auto-update the layout when the browser window is resized.
                container: $('#small_grid'), // Optional, used for some extra CSS styling
                offset: 6, // Optional, the distance between grid items
                itemWidth: 90, // Optional, the width of a grid item (li)
                flexibleItemWidth: true
            };

            // Get a reference to your grid items.
            var handler = $('#small_grid ul li');

            // Call the layout function.
            handler.wookmark(options);

            $('#small_grid ul li > a').attr('rel', 'gallery').colorbox({
                maxWidth	: '80%',
                maxHeight	: '80%',
                opacity		: '0.2',
                loop		: false,
                fixed		: true
            });
        });
    }
};


