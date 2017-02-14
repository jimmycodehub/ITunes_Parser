module.exports = function(grunt){
    // Project configuration.
    grunt.initConfig({
        concat: {
            js: {
                src: ['resources/js/app.js', 'resources/js/**/*.js'],
                dest: 'build/js/scripts.js',
            },
            css: {
                src: ['resources/css/**/*.s+(a|c)ss'],
                dest: 'build/css/styles.scss',
            },
            html: {
                src: ['resources/js/**/**/*.html', 'index.html'],
                dest: 'build/app/main.html',
            }
        },
        watch: {
            js: {
                files: ['resources/js/**/*.js'],
                tasks: ['concat:js'],
                options: {
                    livereload : true,
                }
            },
            css: {
                files: ['resources/css/**/*.s+(a|c)ss'],
                tasks: ['concat:css'],
                options: {
                    livereload : true,
                }
            },
            html: {
                files: ['resources/js/**/**/*.html', 'index.html'],
                tasks: ['concat:html'],
                options: {
                    livereload : true,
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.registerTask('default', ['concat', 'watch'])



}