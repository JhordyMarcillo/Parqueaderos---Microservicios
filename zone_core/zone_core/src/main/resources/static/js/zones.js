document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('createZoneForm');
    if (form) {
        form.addEventListener('submit', async (e) => {
            e.preventDefault();

            const zoneData = {
                name: document.getElementById('name').value,
                capacity: parseInt(document.getElementById('capacity').value),
                type: document.getElementById('type').value,
                description: document.getElementById('description').value,
                isActive: document.getElementById('isActive').checked
            };

            try {
                const response = await fetch('/api/zones', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(zoneData)
                });

                if (response.ok) {
                    alert("¡Zona creada exitosamente!");
                    window.location.href = '/zones';
                } else {
                    const errorText = await response.text();
                    alert("Error al guardar: " + errorText);
                }
            } catch (error) {
                console.error("Error de red:", error);
                alert("Error crítico: No se pudo conectar con el servidor.");
            }
        });
    }

    const deleteButtons = document.querySelectorAll('.btn-delete-zone');
    deleteButtons.forEach(button => {
        button.addEventListener('click', function() {
            const zoneId = this.getAttribute('data-id');
            // Nielsen #5
            if (confirm('¿Estás seguro de eliminar esta zona?')) {
                executeDelete(zoneId);
            }
        });
    });
});
async function executeDelete(id) {
    try {
        const response = await fetch(`/api/zones/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert("Zona eliminada correctamente.");
            location.reload();
        } else {
            // Nielsen #9:
            alert("No se puede eliminar la zona: Verifique que no tenga espacios asociados.");
        }
    } catch (error) {
        console.error("Error de red:", error);
        alert("Error de conexión al intentar eliminar.");
    }
}