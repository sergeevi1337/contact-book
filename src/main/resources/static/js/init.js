ClassicEditor
    .create( document.querySelector( '#image' ), {
        simpleUpload: {
            uploadUrl: '/upload_file',
            headers: {
                'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')
            }
        },
        toolbar: [ 'imageUpload' ]
    } )
    .then( editor => {
        console.log( editor );
    } )
    .catch( error => {
        console.error( error );
} )